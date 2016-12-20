package ma.elaissoussi.ygraph.core;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ma.elaissoussi.ygraph.util.Property;

public class ExtensionSearcher {

    private static final String BIN = "/bin/custom";

    private final ExtensionReader extensionReader = new ExtensionReader();

    public Map<String, List<String>> getAllExtensionDependencies(String hybrisPath) throws IOException {
        final Map<String, List<String>> extensionDependenies = new HashMap<String, List<String>>();

        final List<String> extensionNames = this.extensionReader.getExtensionNames(hybrisPath);

        final Property prop = Property.getInstance();
        final String scanderectory = prop.getPropertyByKey("ygraph.source.hybris.scanderectory");
        final String hybrisBinDirPath = hybrisPath + File.separator + scanderectory;

        final File hybrisBinDirecory = new File(hybrisBinDirPath);

        for (final String extName : extensionNames) {
            final File extensionDirectory = findExtension(extName, hybrisBinDirecory);
            if (extensionDirectory != null) {
                final List<String> requiredExtensionNames = this.extensionReader.getRequiredExtensionNames(extensionDirectory);
                extensionDependenies.put(extName, requiredExtensionNames);
            }
        }
        return extensionDependenies;
    }

    private File findExtension(final String extName, File extDirectory) {
        if (extName.equals(extDirectory.getName()) && this.extensionReader.isExtension(extDirectory)) {
            return extDirectory;
        }

        if (!this.extensionReader.isExtension(extDirectory)) {
            final File[] extDirectories = extDirectory.listFiles();

            if (extDirectories != null && extDirectories.length > 0) {
                for (final File file : extDirectories) {
                    if (file.isDirectory()) {
                        final File resultFile = findExtension(extName, file);

                        if (resultFile != null) {
                            return resultFile;
                        }
                    }
                }
            }
        }
        return null;
    }

}
