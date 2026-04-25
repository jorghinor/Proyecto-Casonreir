# Operación: Notificaciones y Backup

## 1) Notificación por correo al asignar padrino

La notificación se dispara automáticamente al guardar una asignación en:
`POST /config/padrino-becado/guardar`

### Variables/propiedades

En `application.properties`:

- `app.notifications.email.enabled` (default `false`)
- `app.notifications.email.from` (default `no-reply@casonreir.org`)
- `app.notifications.email.audit-to` (default vacío)

Y la configuración SMTP estándar de Spring Mail:

- `spring.mail.host`
- `spring.mail.port`
- `spring.mail.username`
- `spring.mail.password`
- `spring.mail.properties.mail.smtp.auth=true`
- `spring.mail.properties.mail.smtp.starttls.enable=true`

### Ejemplo (entorno)

```bash
export APP_NOTIFICATIONS_EMAIL_ENABLED=true
export APP_NOTIFICATIONS_EMAIL_FROM=no-reply@casonreir.org
export APP_NOTIFICATIONS_EMAIL_AUDIT_TO=admin@casonreir.org
export SPRING_MAIL_HOST=smtp.gmail.com
export SPRING_MAIL_PORT=587
export SPRING_MAIL_USERNAME=tu_correo@gmail.com
export SPRING_MAIL_PASSWORD=tu_app_password
```

## 2) Backup automático diario de PostgreSQL

Script incluido:

`scripts/backup_db.sh`

El script:

- genera backup `.sql.gz`
- guarda en `backups/` (por defecto)
- elimina backups con más de `RETENTION_DAYS` (default `14`)

### Variables soportadas

- `DB_HOST` (default `localhost`)
- `DB_PORT` (default `5432`)
- `DB_NAME` (default `casonreir`)
- `DB_USER` (default `user_admin`)
- `DB_PASSWORD` (default `password_secret`)
- `BACKUP_DIR` (default `/home/jorge/projects/casonreir-backend/backups`)
- `RETENTION_DAYS` (default `14`)

### Ejecución manual

```bash
./scripts/backup_db.sh
```

### Programación diaria con cron (02:00)

```bash
crontab -e
```

Agregar:

```cron
0 2 * * * DB_HOST=localhost DB_PORT=5432 DB_NAME=casonreir DB_USER=user_admin DB_PASSWORD=password_secret BACKUP_DIR=/home/jorge/projects/casonreir-backend/backups RETENTION_DAYS=14 /home/jorge/projects/casonreir-backend/scripts/backup_db.sh >> /home/jorge/projects/casonreir-backend/backups/backup.log 2>&1
```

