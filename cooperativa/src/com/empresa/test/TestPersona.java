package com.empresa.test;

import java.util.ArrayList;
import java.util.List;

import com.empresa.dao.PersonaDao;
import com.empresa.entities.Beneficiario;
import com.empresa.entities.Cliente;
import com.empresa.entities.Direccion;
import com.empresa.entities.Persona;
import com.empresa.entities.Referencia;
import com.empresa.entities.Telefono;

public class TestPersona {

	public static void main(String[]args) {
		Persona persona = new Persona();//cliente
		persona.setNombres("Luis");
		persona.setApellidos("Escobar");
		persona.setNumeroDocumento("as154");
		
		
//		CLIENTE
		
		List<Cliente> cliList = new ArrayList<Cliente>();
		Cliente cli = new Cliente();
		cli.setEsMiembro("1");
		cli.setPersona(persona);
		cliList.add(cli);

		
//	 	TELEFONOS
		
		List<Telefono> telList = new ArrayList<Telefono>();
		Telefono telCli = new Telefono();
		telCli.setTelefono("2334332");
		telCli.setPersona(persona);
		telList.add(telCli);
		
		telList  = new ArrayList<Telefono>();
		telCli = new Telefono();
		telCli.setTelefono("2334423");
		telCli.setPersona(persona);
		telList.add(telCli);
		
		
		
//		DIRECCIONES
		
		List<Direccion> dirList = new ArrayList<Direccion>();
		Direccion dir = new Direccion();
		dir.setDireccion("asdsad");
		dir.setPersona(persona);
		dirList.add(dir);
		
		
//		GUARDANDO LISTA
		
		persona.setDireccions(dirList);
		persona.setTelefonos(telList);
		persona.setClientes(cliList);
		
		PersonaDao perDao = new PersonaDao();
		Persona perRest = perDao.insertPersona(persona);
		System.out.println("id persona insertada " +perRest.getIdPersona());
		
		
//		BENEFICIARIOS
		
		List<Beneficiario> benList = new ArrayList<Beneficiario>();
		Beneficiario ben = new Beneficiario();
		
		Persona perBen = new Persona();
		perBen.setNombres("Ben Nombre 1");
		perBen.setApellidos("Ben Apellido 1");
		perBen.setNumeroDocumento("11111");
		Persona respuestaPerBen = perDao.insertPersona(perBen);
		

		ben.setCliente(persona.getClientes().get(0));
		ben.setPersona(respuestaPerBen);
		ben.setEdad(19);
		ben.setParentesco("Primo");
		ben.setPorcentaje(100);
		benList.add(ben);
		cli.setBeneficiarios(benList);
		
		String respuestaBen = perDao.insertBeneficiarios(benList);
		System.out.println("Respuesta Beneficiarios "+respuestaBen );		
		
		
		
// 		REFERENCIAS
		
		List<Referencia> refList = new ArrayList<Referencia>();
		Referencia ref = new Referencia();
		
		Persona perRef = new Persona();
		perRef.setNombres("Ref Nombre 2");
		perRef.setApellidos("Ref Apellido 2");
		perRef.setNumeroDocumento("222222");
		Persona respuestaPerRef = perDao.insertPersona(perRef);
		
		
		ref.setPersona(respuestaPerRef);
		ref.setCliente(persona.getClientes().get(0));
		refList.add(ref);
		cli.setReferencias(refList);
		
		String respuestaRef = perDao.insertReferencias(refList);
		System.out.println("Respuesta Referencia "+respuestaRef );
		
		
		
		
		
		
		
	
	}
}
