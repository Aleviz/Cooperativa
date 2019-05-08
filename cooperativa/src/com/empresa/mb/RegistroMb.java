package com.empresa.mb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.empresa.dao.PersonaDao;
import com.empresa.entities.Beneficiario;
import com.empresa.entities.Cliente;
import com.empresa.entities.Direccion;
import com.empresa.entities.Persona;
import com.empresa.entities.PersonaGenerica;
import com.empresa.entities.Referencia;
import com.empresa.entities.Telefono;

@ManagedBean
@ViewScoped
public class RegistroMb {

	private Persona persona;
	private List<Cliente> cliList;
	private Cliente cliente;
	private List<Direccion> cliDirList;
	private List<Telefono> telCliList;
	private List<PersonaGenerica> referencias;
	private List<PersonaGenerica> beneficiarios;
	private List<Referencia> refCliList;
	private List<Beneficiario> benRefCliList;
	private PersonaDao perDao;
	
	
	
	@PostConstruct
	public void init() {
		persona = new Persona();
		cliList = new ArrayList<Cliente>();
		cliente = new Cliente();
		cliDirList = new ArrayList<Direccion>();
		telCliList = new ArrayList<Telefono>();
		referencias = new ArrayList<PersonaGenerica>();
		beneficiarios = new ArrayList<PersonaGenerica>();
		
		refCliList = new ArrayList<Referencia>();
		benRefCliList = new ArrayList<Beneficiario>();
		perDao = new PersonaDao();
	}
	
	public void insertCliente() {
		cliente.setPersona(persona);
		cliList.add(cliente);
		persona.setClientes(cliList);
		
		for(Direccion dir:cliDirList) {
			dir.setPersona(persona);
		}
		persona.setDireccions(cliDirList);
		
		
		for(Telefono tel:telCliList) {
			tel.setPersona(persona);
		}
		persona.setTelefonos(telCliList);
		
		
		for(PersonaGenerica per:beneficiarios) {
			Beneficiario ben = new Beneficiario();
			ben.setCliente(cliente);
			ben.setEdad(per.getEdad());
			ben.setParentesco(per.getParentesco());
			Persona perBen = new Persona();
			perBen.setNombres(per.getNombre());
			perBen.setApellidos(per.getApellidos());
			perBen.setNumeroDocumento(per.getNumeroDocumento());
			Persona perRespuesta = perDao.insertPersona(perBen);
			
			ben.setPersona(perRespuesta);
			ben.setPorcentaje(per.getPorcentaje());
			benRefCliList.add(ben);
			}		
		persona.setBeneficiarios(benRefCliList);
		
		for(PersonaGenerica perGen:referencias) {
			Referencia ref = new Referencia();
			ref.setCliente(cliente);
			Persona perRef = new Persona();
			perRef.setNombres(perGen.getNombre());
			perRef.setApellidos(perGen.getApellidos());
			perRef.setNumeroDocumento(perGen.getNumeroDocumento());
			Persona personaGenRespuesta = perDao.insertPersona(perRef);
			ref.setPersona(personaGenRespuesta);
			ref.setTiempoconocerse(perGen.getTiempoconocerse());
			refCliList.add(ref);
		}			
		persona.setReferencias(refCliList);
		
		
		Persona clienteGuardado = perDao.insertPersona(persona);
		FacesMessage msg = new FacesMessage("persona guardada "+clienteGuardado);
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
	}
	
	public void addDireccion() {
		cliDirList.add(new Direccion());
	}

	public void addTelefonoCliente() {
		telCliList.add(new Telefono());
	}
	
	public void addReferencias() {
		referencias.add(new PersonaGenerica());
	}

	public void addBeneficiarios() {
		beneficiarios.add(new PersonaGenerica());
	}
	
	public String iraCuentas() {
		return "cuentas.xhtml";
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public List<Cliente> getCliList() {
		return cliList;
	}

	public void setCliList(List<Cliente> cliList) {
		this.cliList = cliList;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Direccion> getCliDirList() {
		return cliDirList;
	}

	public void setCliDirList(List<Direccion> cliDirList) {
		this.cliDirList = cliDirList;
	}

	public List<Telefono> getTelCliList() {
		return telCliList;
	}

	public void setTelCliList(List<Telefono> telCliList) {
		this.telCliList = telCliList;
	}

	public List<PersonaGenerica> getReferencias() {
		return referencias;
	}

	public void setReferencias(List<PersonaGenerica> referencias) {
		this.referencias = referencias;
	}

	public List<PersonaGenerica> getBeneficiarios() {
		return beneficiarios;
	}

	public void setBeneficiarios(List<PersonaGenerica> beneficiarios) {
		this.beneficiarios = beneficiarios;
	}

	public List<Referencia> getRefCliList() {
		return refCliList;
	}

	public void setRefCliList(List<Referencia> refCliList) {
		this.refCliList = refCliList;
	}

	public List<Beneficiario> getBenRefCliList() {
		return benRefCliList;
	}

	public void setBenRefCliList(List<Beneficiario> benRefCliList) {
		this.benRefCliList = benRefCliList;
	}
	
	
	

	
	
}
