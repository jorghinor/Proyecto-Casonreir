package com.casonreir.service;

import com.casonreir.model.Becado;
import com.casonreir.model.InformacionPersonal;
import com.casonreir.model.Padrino;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class NotificationEmailService {

    private static final Logger log = LoggerFactory.getLogger(NotificationEmailService.class);
    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final JavaMailSender mailSender;

    @Value("${app.notifications.email.enabled:false}")
    private boolean enabled;

    @Value("${app.notifications.email.from:no-reply@casonreir.org}")
    private String from;

    @Value("${app.notifications.email.audit-to:}")
    private String auditTo;

    public NotificationEmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendPadrinoAsignadoNotification(Padrino padrino, Becado becado, LocalDate fechaInicio) {
        if (!enabled) {
            return;
        }

        String to = safeEmail(padrino != null ? padrino.getInformacionPersonal() : null);
        if (!StringUtils.hasText(to)) {
            log.warn("No se envió correo de asignación: padrino sin correo registrado");
            return;
        }

        String nombrePadrino = fullName(padrino != null ? padrino.getInformacionPersonal() : null, "Padrino");
        String nombreBecado = fullName(becado != null ? becado.getInformacionPersonal() : null, "Becado");
        String inicio = fechaInicio != null ? fechaInicio.format(DATE_FMT) : "N/D";

        String subject = "Asignación confirmada de padrino";
        String body = "Hola " + nombrePadrino + ",\n\n"
                + "Se registró una nueva asignación de apoyo en el sistema.\n"
                + "Becado asignado: " + nombreBecado + "\n"
                + "Fecha de inicio: " + inicio + "\n\n"
                + "Gracias por su apoyo.\n"
                + "Fundación Casonreir";

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from);
            message.setTo(to);
            if (StringUtils.hasText(auditTo) && !auditTo.equalsIgnoreCase(to)) {
                message.setCc(auditTo);
            }
            message.setSubject(subject);
            message.setText(body);
            mailSender.send(message);
        } catch (Exception ex) {
            log.error("Error al enviar correo de asignación padrino-becado: {}", ex.getMessage(), ex);
        }
    }

    private String safeEmail(InformacionPersonal info) {
        if (info == null || !StringUtils.hasText(info.getCorreoE())) return null;
        return info.getCorreoE().trim();
    }

    private String fullName(InformacionPersonal info, String fallback) {
        if (info == null) return fallback;
        String nombre = StringUtils.hasText(info.getNombre()) ? info.getNombre().trim() : "";
        String ap = StringUtils.hasText(info.getApellidoPat()) ? info.getApellidoPat().trim() : "";
        String am = StringUtils.hasText(info.getApellidoMat()) ? info.getApellidoMat().trim() : "";
        String full = (nombre + " " + ap + " " + am).trim().replaceAll("\\s+", " ");
        return StringUtils.hasText(full) ? full : fallback;
    }
}
