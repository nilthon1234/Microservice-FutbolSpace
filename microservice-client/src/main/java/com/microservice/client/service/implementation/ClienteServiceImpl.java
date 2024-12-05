package com.microservice.client.service.implementation;

import com.microservice.client.persistence.models.Cliente;
import com.microservice.client.persistence.repository.IClienteRepository;
import com.microservice.client.presentation.dto.CampoFutbolDto;
import com.microservice.client.presentation.dto.ClienteDto;
import com.microservice.client.presentation.dto.UsuarioDto;
import com.microservice.client.service.http.response.ResponseCliente;
import com.microservice.client.service.interfaces.IClienteservice;
import com.microservice.client.utils.interfaces.CampoFutbolClient;
import com.microservice.client.utils.interfaces.UsuarioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements IClienteservice {
    @Autowired
    private IClienteRepository iClienteRepository;

    @Autowired
    private CampoFutbolClient campoFutbolClient;
    @Autowired
    private UsuarioClient usuarioClient;
    @Override
    public void addClient(Cliente cliente){
        iClienteRepository.save(cliente);
    }

    @Override
    public ResponseCliente listAllAcces() {
        List<CampoFutbolDto> campodto = campoFutbolClient.listAllAccesCampo();
        List<UsuarioDto> usuarioDto = usuarioClient.listAllAccessUsuario();
        return ResponseCliente.builder()
                .campoFutbolDtoList(campodto)
                .usuarioDtoList(usuarioDto)
                .build();
    }

    @Override
    public Map<String, String> login(int dni, String password) {
        Optional<Cliente> validar = iClienteRepository.findByDniAndPassword(dni, password);
        Map<String, String > response = new HashMap<>();
        if (!validar.isPresent()){
            response.put("error", "credenciales invalidas");
            return response;
        }
        Cliente cli = validar.get();
        response.put("access", "Axidistes Exitosamente: " + cli.getName());
        return response;
    }

	@Override
	public List<ClienteDto> listAllCliente() {
		// TODO Auto-generated method stub
		return iClienteRepository.findAll().stream()
				.map(ClienteDto::new)
				.collect(Collectors.toList());
	}
}
