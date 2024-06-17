import java.net.URI;
import java.lang.ProcessBuilder;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

class Yt2mp3{

	public Yt2mp3(String link){
		System.out.println("Validating link...");
		validateLink(link);
		System.out.println("Converting youtube link: "+ link);
		runYTDLP(link);
	}

	private void validateLink(String inputLink){
		String pattern = "^(http(s)?:\\/\\/)?((w){3}.)?youtu(be|.be)?(\\.com)?\\/.+";
		if(inputLink.isEmpty() || !inputLink.matches(pattern)){
			System.err.println("Error: must be Youtube link with valid format.");
			System.exit(0);
		}
		try {
			new URI(inputLink).toURL();
			System.out.println("valid link");
		}catch (Exception e){
			System.err.println("Not a valid youtube link.");
		}
	}

	private void runYTDLP(String link){
		try{
			String[] commands = {"yt-dlp", link};
			ProcessBuilder pb = new ProcessBuilder(commands);
			Process process = pb.start();
			//read input
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line;
			while((line = br.readLine())!= null){
				System.out.println(line);
			}

			try {
				//process terminate
				process.waitFor();
			} catch (InterruptedException e) {
			    e.printStackTrace();
			}

		}catch (IOException e){
			e.printStackTrace();
		}
	}
	

	public static void main(String[] args){
		if(args.length < 1){
			System.err.println("No input provided.");
			System.exit(0);
		}
		String inputLink = args[0];
		Yt2mp3 obj = new Yt2mp3(inputLink);
	}
}
