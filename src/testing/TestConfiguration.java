package testing;
import configuration.*;
import material.*;
import java.sql.SQLException;
import java.util.ArrayList;
public class TestConfiguration {

	public static void main(String[] args) throws SQLException{
		// TODO Auto-generated method stub
		Configuration conf = new Configuration();
		ArrayList<MotherBoard> mb = Choose.chooseMotherBoard(conf);
		for(int i = 0; i < mb.size(); i++)
		{
			System.out.println(mb.get(i).getId() + " " + mb.get(i).getName());
		}
		conf.setMother(mb.get(4));
		
		System.out.println("\n\nCase: ");
		ArrayList<Case> cs = Choose.chooseCase(conf);
		for(int i = 0; i < cs.size(); i++)
		{
			System.out.println(cs.get(i).getId() + " " + cs.get(i).getName());
		}
		
		//System.out.println(conf.isComplete());
		conf.setCas(cs.get(0));
		
		System.out.println("\n\nHddSsd: ");
		ArrayList<HddSsd> hs = Choose.chooseDisk(conf);
		for(int i = 0; i < hs.size(); i++)
		{
			System.out.println(hs.get(i).getId() + " " + hs.get(i).getName());
		}
		
		conf.setDisk(hs.get(0));
		
		System.out.println("\n\nHeatSink: ");
		ArrayList<Heatsink> hss = Choose.chooseHeatsink(conf);
		for(int i = 0; i < hss.size(); i++)
		{
			System.out.println(hss.get(i).getId() + " " + hss.get(i).getName());
		}
		
		conf.setHeat(hss.get(0));
		
		System.out.println("\n\nPowerPack: ");
		ArrayList<PowerPack> pp = Choose.choosePowerPack(conf);
		for(int i = 0; i < pp.size(); i++)
		{
			System.out.println(pp.get(i).getId() + " " + pp.get(i).getName());
		}
		
		conf.setPower(pp.get(0));
		
		System.out.println("\n\nProcessor: ");
		ArrayList<Processor> pr = Choose.chooseProcessor(conf);
		for(int i = 0; i < pr.size(); i++)
		{
			System.out.println(pr.get(i).getId() + " " + pr.get(i).getName());
		}
		
		conf.setProcess(pr.get(2));
		
		System.out.println("\n\nRam: ");
		ArrayList<Ram> rm = Choose.chooseRam(conf);
		for(int i = 0; i < rm.size(); i++)
		{
			System.out.println(rm.get(i).getId() + " " + rm.get(i).getName());
		}
		
		conf.setRam(rm.get(1));
		
		System.out.println("\n\nVideoCard: ");
		ArrayList<VideoCard> vc = Choose.chooseVideoCard(conf);
		for(int i = 0; i < vc.size(); i++)
		{
			System.out.println(vc.get(i).getId() + " " + vc.get(i).getName());
		}
		
		conf.setVideoc(vc.get(3));
		
		System.out.println(conf.isComplete());
	}

}
