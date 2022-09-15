package rudok.gui.controller;

import lombok.Getter;
import lombok.Setter;

import javax.swing.filechooser.FileFilter;
import java.io.File;
@Setter
@Getter
public class ProjectFileFilter extends FileFilter {

    @Override
    public boolean accept(File f) {
        return (f.isDirectory() || f.getName().toLowerCase().endsWith(".rdk"));
    }

    @Override
    public String getDescription() {
        return "RuDok project files (*.rdk)";
    }
}