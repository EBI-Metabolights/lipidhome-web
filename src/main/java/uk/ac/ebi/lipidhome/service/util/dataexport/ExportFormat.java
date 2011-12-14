package uk.ac.ebi.lipidhome.service.util.dataexport;

public enum ExportFormat {
    NONE("",""),
    CSV("csv","csv"),
    TSV("tsv", "tsv"),
    EXCEL("excel", "xls"),
    JSON("json", "json"),
    XML("xml", "xml");

    private String format;
    private String extension;

    ExportFormat(String format, String extension){
        this.format = format;
        this.extension = extension;
    }

    public boolean isFormat(String format){
        return this.format.equals(format.toLowerCase());
    }

    public String getExtension(){
        return extension;
    }

    public static ExportFormat getFormat(String format){
        for (ExportFormat exportFormat : ExportFormat.values()) {
            if(exportFormat.isFormat(format)) return exportFormat;
        }
        return NONE;
    }
}
