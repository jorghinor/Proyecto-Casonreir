package com.casonreir.service;

import com.casonreir.dto.BecadoDTO;
import com.casonreir.model.Becado;
import com.casonreir.model.InformacionPersonal;
import com.casonreir.repository.BecadoRepository;
import com.casonreir.repository.InformacionPersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BecadoService {

    @Autowired
    private BecadoRepository becadoRepository;

    @Autowired
    private InformacionPersonalRepository infoRepository;

    @Transactional
    public Becado guardarBecadoCompleto(BecadoDTO dto) {
        // 1. Mapear y guardar la Información Personal
        InformacionPersonal info = new InformacionPersonal();
        info.setNombre(dto.getNombre());
        info.setApellidoPat(dto.getApellidoPat());
        info.setApellidoMat(dto.getApellidoMat());
        info.setCi(dto.getCi());
        info.setFechaNac(dto.getFechaNac());
        info.setDireccion(dto.getDireccion());
        
        info = infoRepository.save(info);

        // 2. Mapear y guardar el Becado vinculado a la info anterior
        Becado becado = new Becado();
        becado.setInformacionPersonal(info);
        becado.setNumHermanos(dto.getNumHermanos());
        becado.setIngresoBecado(dto.getIngresoBecado());
        becado.setEstadoBecado(dto.getEstadoBecado());

        return becadoRepository.save(becado);
    }
}