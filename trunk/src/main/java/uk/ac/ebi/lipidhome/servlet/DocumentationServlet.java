package uk.ac.ebi.lipidhome.servlet;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * The DocumentationServlet provides a way for constructing the documentation hierarchy by instrospecting the
 * 'resources/static/documentation' static folder (due to security reasons static folders listing does not use
 * to be allowed)
 *
 */
public class DocumentationServlet extends HttpServlet {

    public DocumentationServlet() {
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private boolean isValidPath(String path){
        if(path==null)
            return false;

        if(!path.isEmpty()){
            String[] folders = path.split("/");

            for (String folder : folders)
                if(folder.equals(".."))
                    return false;
        }
        return true;
    }


    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String DOC_PATH = getServletContext().getRealPath("resources/static/documentation");
        String path = req.getParameter("path");

        JSONObject rtn = new JSONObject();
        if(!isValidPath(path)){
            try {
                rtn.put("success", false);
                rtn.put("errorMsg", "The provided path is invalid");
            } catch (JSONException e) {}
        }else{
            File f = new File(DOC_PATH + path);
            if(f.isDirectory()){
                JSONObject content = new JSONObject();

                List<String> files = new ArrayList<String>();
                List<String> folders = new ArrayList<String>();

                for (File file : f.listFiles()) {
                    String name = file.getName();
                    if(name.startsWith(".")) continue;
                    //Only HTML files are allowed
                    if(file.isFile()){
                        if(!name.endsWith(".html")) continue;
                        files.add(name);
                    }else{
                        folders.add(name);
                    }
                }
                Collections.sort(files);
                Collections.sort(folders);
                try {
                    for (String file : files) {
                        content.accumulate("files", file);
                    }
                    for (String folder : folders) {
                        content.accumulate("folders", folder);
                    }
                    rtn.put("success", true);
                    rtn.put("content", content);
                } catch (JSONException e) {}
            }else{
                try {
                    rtn.put("success", false);
                    rtn.put("errorMsg", "The path does not point to a folder");
                } catch (JSONException e) {}
            }
        }

        try {
			PrintWriter out = resp.getWriter();
			out.println(rtn.toString());
			out.close();
		} catch (IOException e) {
			System.err.println(e);
		}
    }
}
