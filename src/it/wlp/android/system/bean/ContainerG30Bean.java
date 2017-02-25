package it.wlp.android.system.bean;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root
public class ContainerG30Bean 
{
	@ElementList
	public List<G30Bean> g30Beans =  new ArrayList<G30Bean>();

	
	
	
}
