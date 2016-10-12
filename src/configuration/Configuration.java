package configuration;
import material.*;
public class Configuration {

	private Case cas;
	private HddSsd disk;
	private Heatsink heat;
	private MotherBoard mother;
	private PowerPack power;
	private Processor process;
	private Ram ram;
	private VideoCard videoc;
	
	public Configuration(){}
	
	public Case getCas() {
		return cas;
	}
	public void setCas(Case cas) {
		this.cas = cas;
	}
	public HddSsd getDisk() {
		return disk;
	}
	public void setDisk(HddSsd disk) {
		this.disk = disk;
	}
	public Heatsink getHeat() {
		return heat;
	}
	public void setHeat(Heatsink heat) {
		this.heat = heat;
	}
	public MotherBoard getMother() {
		return mother;
	}
	public void setMother(MotherBoard mother) {
		this.mother = mother;
	}
	public PowerPack getPower() {
		return power;
	}
	public void setPower(PowerPack power) {
		this.power = power;
	}
	public Processor getProcess() {
		return process;
	}
	public void setProcess(Processor process) {
		this.process = process;
	}
	public Ram getRam() {
		return ram;
	}
	public void setRam(Ram ram) {
		this.ram = ram;
	}
	public VideoCard getVideoc() {
		return videoc;
	}
	public void setVideoc(VideoCard videoc) {
		this.videoc = videoc;
	}
	public boolean isComplete()
	{
		if(this.cas != null && this.disk != null && this.heat != null && this.mother != null && this.power != null && this.process != null && this.ram != null && this.videoc != null) return true;
		else return false;
	}
}
