package ma.elaissoussi.ygraph.core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ExtensionReader 
{		
	private static final String EXTENSION = "extension";
	private static final String CONFIG = "config";
	private static final String LOCALEXTENSIONS_XML = "localextensions.xml";
	private static final String EXTENSIONINFO_XML = "extensioninfo.xml";
	private static final String NAME = "name";
	private static final String REQUIRES_EXTENSION = "requires-extension";

	
	public List<String> getExtensionNames(String hybrisPath) 
	{
		List<String> extensionNames = new ArrayList<String>();  

		String localExtensionsPath = hybrisPath + File.separator + CONFIG + File.separator + LOCALEXTENSIONS_XML;
		File localExtensionsFile = new File(localExtensionsPath);
		
		if(localExtensionsFile.exists())
		{
			NodeList extensions = getNodeListForTagName(localExtensionsFile, EXTENSION);
			fillNameList(extensionNames, extensions);
	    }
		
		return extensionNames; 		
	}
	
	public List<String> getRequiredExtensionNames(File extensionDirectory) 
	{
		List<String> requiredExtensionNames = new ArrayList<String>();
		
		String extensionInfos = extensionDirectory.getAbsolutePath() + File.separator + EXTENSIONINFO_XML;
		File extensionInfosFile = new File(extensionInfos); 
		
		if(extensionInfosFile.exists())
		{
			NodeList requiredExtenions = getNodeListForTagName(extensionInfosFile, REQUIRES_EXTENSION);
			fillNameList(requiredExtensionNames, requiredExtenions);
		}
		
		return requiredExtensionNames; 
	}
	
	private void fillNameList(List<String> extensionNames, NodeList extensionNodes) 
	{
		if( extensionNodes != null )
		{
			int extensionsLength = extensionNodes.getLength();
	
			for (int i = 0; i < extensionsLength; i++) 
			{
				Node extNode = extensionNodes.item(i);
				Element extElement = (Element) extNode;
				String extensionName = extElement.getAttribute(NAME);
				extensionNames.add(extensionName);
			}
		}
	}
	
	private NodeList getNodeListForTagName(File extensionFile ,String tagName) 
	{
       NodeList extenionNodeList = null ; 
		try 
		{
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder	docBuilder = docFactory.newDocumentBuilder();
			Document docLocalExtnsions = docBuilder.parse(extensionFile);
			extenionNodeList =   docLocalExtnsions.getElementsByTagName(tagName);
		} 
		catch (ParserConfigurationException e) 
		{
			e.printStackTrace();
			
		} catch (SAXException e) 
		{
			e.printStackTrace();
			
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		return extenionNodeList ;
	}
	
	public boolean isExtension(File file)
	{
		if(file.isDirectory())
		{
			String localExtensionPath = file.getAbsoluteFile() + File.separator +  EXTENSIONINFO_XML;
			File localExtfile =  new File(localExtensionPath);
			return localExtfile.exists();
		}	
		return false ; 
	}
}
