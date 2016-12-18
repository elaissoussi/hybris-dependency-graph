package ma.elaissoussi.ygraph.core;

import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExtensionSearcher 
{
	private static final String BIN = "/bin";

	private ExtensionReader extensionReader = new ExtensionReader();

	public Map<String, List<String>> getAllExtensionDependencies(String hybrisPath) 
	{
	    Map<String, List<String>> extensionDependenies = new HashMap<String, List<String>>(); 
		
	    List<String> extensionNames = extensionReader.getExtensionNames(hybrisPath);
		
		String hybrisBinDirPath = hybrisPath + File.separator + BIN;
		
		File hybrisBinDirecory = new File(hybrisBinDirPath); 
		
		for (String extName : extensionNames) 
		{
			File extensionDirectory = findExtension(extName, hybrisBinDirecory);
			List<String> requiredExtensionNames = extensionReader.getRequiredExtensionNames(extensionDirectory);
			extensionDependenies.put(extName, requiredExtensionNames);
		}
		return extensionDependenies;
	}

	private File findExtension(final String extName, File extDirectory) 
	{
		if (extName.equals(extDirectory.getName()) && extensionReader.isExtension(extDirectory)) 
		{
			return extDirectory;
		}
		
		if(!extensionReader.isExtension(extDirectory) )
		{
			File[] extDirectories = extDirectory.listFiles();
			
			if (extDirectories != null && extDirectories.length > 0) 
			{
				for (File file : extDirectories) 
				{
					if (file.isDirectory()) 
					{
						File resultFile = findExtension(extName, file);
	
						if (resultFile != null) 
						{
							return resultFile;
						}
					}
				}
			}
		}
		return null;
	}
	
}
