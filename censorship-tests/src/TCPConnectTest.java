import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class TCPConnectTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		String inputfile;
		String defaultinput = "outsideresults";
		try{
			inputfile = args[0];
		}catch(Exception e){
			System.out.println("Defaulting to input file " + defaultinput);
			inputfile = defaultinput;
		}
		
		int cores = Runtime.getRuntime().availableProcessors()-1;
		cores = 2000;
		ThreadPoolExecutor es = (ThreadPoolExecutor) Executors.newFixedThreadPool(cores);
		
		try(BufferedReader br = new BufferedReader(new FileReader(inputfile))) {
		    for(String line; (line = br.readLine()) != null; ) {
		        
		    	final String linef = line;
		    	Runnable run = (new Runnable() {
					
					@Override
					public void run() {
						
						
						String serverN = linef;
						try{
							//if there is a ,
							serverN = serverN.substring(0, serverN.indexOf(','));
						}catch(StringIndexOutOfBoundsException e){}
						serverN = serverN.replaceAll("http://", "");
						serverN = serverN.replaceAll("https://", "");
						serverN = serverN.replaceAll("ftp://", "");
						serverN = serverN.replaceAll("ftps://", "");
						serverN = serverN.replaceAll("/", "");
						serverN = serverN.trim();
						serverN = serverN.toLowerCase();
						
				    	String result = "Error";
				    	//int trys = 0;
				    	//while (trys-- >= 0 && !("Success".equals(result))){
					    	try{
					    		testConnection(serverN);
					    		result = "Success";
					    	}catch(Exception e){
					    		result = e.getClass().getSimpleName();
//					    		try {
//					    			//Sleep for a moment before trying again
//									Thread.sleep(5000);
//								} catch (InterruptedException e1) {
//									e1.printStackTrace();
//								}
					    	}
				    	//}
				    	
				    	System.out.println(serverN + "," + 80 + "," + result);
				    	
		    	
					}
				});
		    	
		    	es.execute(run);
		    	
		    }
		   
		    
		}
		
		es.shutdown();
		
		//wait forever
		es.awaitTermination(9999, TimeUnit.DAYS);
		System.out.println("#######Finished######");
		
		
		

	}
	
	
	
	
	
	public static void testConnection(String server) throws UnknownHostException, IOException{
		
		URL u = new URL("http://" + server);
		URLConnection c = u.openConnection();
		
		c.connect();
		
		//System.out.println(c.getContentLength());
		
		
//		Socket client = new Socket(server, port);
//        System.out.println("Just connected to " + client.getRemoteSocketAddress());
//        OutputStream outToServer = client.getOutputStream();
//        DataOutputStream out = new DataOutputStream(outToServer);
//
//        out.writeUTF("GET / HTTP/1.1\n\n");
//        InputStream inFromServer = client.getInputStream();
//        DataInputStream in = new DataInputStream(inFromServer);
//        System.out.println("Server says " + in.readUTF());
//        client.close();
		
	}
	
	
	

}
