package bg.tu_varna.sit.a1.f21621531.commonCommands;

import bg.tu_varna.sit.a1.f21621531.xmlParser.XmlFile;

public interface XmlFileAwareCommand extends Command {
    void setXmlFile(XmlFile xmlFile);
    XmlFile getXmlFile();
}
