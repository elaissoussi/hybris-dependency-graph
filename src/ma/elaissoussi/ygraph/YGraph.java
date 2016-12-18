package ma.elaissoussi.ygraph ; 

import java.util.Map;

import javax.swing.JFrame;

import ma.elaissoussi.ygraph.core.ExtensionSearcher;
import ma.elaissoussi.ygraph.graph.DependenciesViewer;

public class YGraph
{
	public static void main(String[] args) 
	{
//		ExtensionSearcher searcher = new ExtensionSearcher();
//		 String hybrisPath = "D:\\EL AISSOUSSI\\certification\\Hybris\\1-Hybris\\env\\source\\hybris.5.2.0\\hybris";
//		 Map allExtensionDependencies = searcher.getAllExtensionDependencies(hybrisPath);
//		System.out.print(allExtensionDependencies);
		
		DependenciesViewer dg = new DependenciesViewer();
		dg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dg.setSize(500, 500);
		dg.setVisible(true);
	}
}
