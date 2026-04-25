#!/usr/bin/env bash
# ==============================================================================
# SCRIPT DE RESPALDO TOTAL - FUNDACIÓN CASONREIR
# ==============================================================================

# 1. Configuración
PROJECT_DIR="/home/jorge/projects/casonreir-backend"
BACKUP_DIR="${PROJECT_DIR}/backups"
TIMESTAMP=$(date +"%Y%m%d_%H%M%S")
BACKUP_NAME="CASONREIR_FULL_BACKUP_${TIMESTAMP}"
DB_CONTAINER="casonreir_db"

echo ">>> Iniciando proceso de respaldo integral..."

# Crear carpeta de backups si no existe
mkdir -p "${BACKUP_DIR}"

# 2. Respaldo de Base de Datos (Vía Docker Exec para evitar errores de red)
echo ">>> [1/3] Extrayendo base de datos desde el contenedor..."
docker exec "${DB_CONTAINER}" pg_dump -U user_admin -d casonreir > "${BACKUP_DIR}/database_${TIMESTAMP}.sql"

if [ $? -eq 0 ]; then
    echo ">>> Base de Datos extraída con éxito."
else
    echo ">>> [ERROR] No se pudo extraer la base de datos."
    exit 1
fi

# 3. Empaquetar todo el sistema
echo ">>> [2/3] Comprimiendo código fuente, archivos subidos y base de datos..."
cd "${PROJECT_DIR}"
tar -czf "${BACKUP_DIR}/${BACKUP_NAME}.tar.gz" \
    src \
    uploads \
    pom.xml \
    docker-compose.yml \
    migration.load \
    -C "${BACKUP_DIR}" "database_${TIMESTAMP}.sql"

# 4. Limpieza de archivos temporales
rm "${BACKUP_DIR}/database_${TIMESTAMP}.sql"

echo ">>> [3/3] Proceso finalizado."
echo "=========================================================="
echo "ARCHIVO GENERADO: ${BACKUP_DIR}/${BACKUP_NAME}.tar.gz"
echo "=========================================================="

# Mantener solo los últimos 7 días de backups para ahorrar espacio
find "${BACKUP_DIR}" -name "CASONREIR_FULL_BACKUP_*.tar.gz" -mtime +7 -delete
echo ">>> Limpieza de backups antiguos (7 días) completada."
