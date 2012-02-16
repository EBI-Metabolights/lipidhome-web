package uk.ac.ebi.lipidhome.service.result;

import com.google.gson.Gson;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * This class writes results to an output stream after using the Google Gson library to write a result as a json
 * formatted string.
 *
 */
public class ResultStreamingOutput implements StreamingOutput {

	private Result result;
	
	private String callback = null;
	
	public ResultStreamingOutput(Result result){
		this.result = result;
	}
	
	public ResultStreamingOutput(Result result, String callback){
		this.result = result;
		this.callback = callback;
	}
	
	@Override
	public void write(OutputStream output) throws IOException, WebApplicationException {
		Gson gson = new Gson();
		String res = gson.toJson(result);
		if(callback!=null && !callback.isEmpty()){
			res = callback + "(" + res + ");"; 
		}
		
		output.write(res.getBytes());
	}
}
