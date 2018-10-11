package service.AAAHDISINIESTRO;

public class autoProntoBean {

	private int Id;
	private int NumContrato;
	private long NumSiniestro;
	private String Nombre;
	private String FechaIngreso;
	private String FechaSalida;
	private String Status;
	private String Mobile;
	private String LinkRazon;
	private String LinkDetalleCliente;
	
	public autoProntoBean(){
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public int getNumContrato() {
		return NumContrato;
	}
	public void setNumContrato(int numContrato) {
		NumContrato = numContrato;
	}
	public long getNumSiniestro() {
		return NumSiniestro;
	}
	public void setNumSiniestro(long numSiniestro) {
		NumSiniestro = numSiniestro;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getFechaIngreso() {
		return FechaIngreso;
	}
	public void setFechaIngreso(String fechaIngreso) {
		FechaIngreso = fechaIngreso;
	}
	public String getFechaSalida() {
		return FechaSalida;
	}
	public void setFechaSalida(String fechaSalida) {
		FechaSalida = fechaSalida;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getMobile() {
		return Mobile;
	}
	public void setMobile(String mobile) {
		Mobile = mobile;
	}
	public String getLinkRazon() {
		return LinkRazon;
	}
	public void setLinkRazon(String linkRazon) {
		LinkRazon = linkRazon;
	}
	public String getLinkDetalleCliente() {
		return LinkDetalleCliente;
	}
	public void setLinkDetalleCliente(String linkDetalleCliente) {
		LinkDetalleCliente = linkDetalleCliente;
	}
	
	@Override
	public String toString() {
		return "autoProntoBean [Id=" + Id + ", NumContrato=" + NumContrato
				+ ", NumSiniestro=" + NumSiniestro + ", Nombre=" + Nombre
				+ ", FechaIngreso=" + FechaIngreso + ", FechaSalida="
				+ FechaSalida + ", Status=" + Status + ", Mobile=" + Mobile
				+ ", LinkRazon=" + LinkRazon + ", LinkDetalleCliente="
				+ LinkDetalleCliente + "]";
	}
}
