import java.net.URI;
import java.lang.ProcessBuilder;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;

class Yt2mp3{

	private String filename;
	private String link;

	public Yt2mp3(){
		//validateLink(link);
		//runYTDLP(link);
		this.link = "";
		this.filename = "";
	}

	private void validateLink(String inputLink){
		System.out.println("Validating link...");
		String pattern = "^(http(s)?:\\/\\/)?((w){3}.)?youtu(be|.be)?(\\.com)?\\/.+";
		if(inputLink.isEmpty() || !inputLink.matches(pattern)){
			System.err.println("Error: must be Youtube link with valid format.");
			System.exit(0);
		}
		try {
			new URI(inputLink).toURL();
		}catch (Exception e){
			System.err.println("Not a valid youtube link.");
		}
	}

	private void runYTDLP(String link){
		System.out.println("Extracting audio...");
		List<String> command = new ArrayList<>(Arrays.asList("yt-dlp", "--extract-audio", "--audio-quality", "0", "-o", "output/%(title)s.%(ext)s", link));
		runProcesses(command, "download");
		convertToMP3();
	}

	private void downloadProgress(String line){
		if(line.length() <= 1){
			return;
		}
		int i = line.indexOf(' ');
		String firstWord = line.substring(0, i);
		if(firstWord.equals("[download]")){
			System.out.println(line);
		}else if(firstWord.equals("[ExtractAudio]")){
			getTitle(line);
		}
	}

	private void getTitle(String line){
		int i = line.indexOf('/');
		filename = line.substring(i);
	}

	private String audioTitle(){
		String title = "";
		if(filename != null && filename.contains(".")){
			title = filename.substring(0, filename.lastIndexOf('.'));
		}
		System.out.println(title);
		return title;
	}

	private void convertToMP3(){
		System.out.println("Converting to mp3...");
		String audioPath = "output/"+filename;
		String outputFile = "output/"+audioTitle()+".mp3";
		List<String> command = new ArrayList<>(Arrays.asList("ffmpeg", "-i", audioPath, outputFile));
		runProcesses(command, "convert");
		DeleteOGAudioFile();
	}

	private void DeleteOGAudioFile(){
		List<String> command = new ArrayList<>(Arrays.asList("rm", "output/"+filename));
		runProcesses(command, "delete");
		System.out.println("Finished! audio in output folder.");
	}

	private void runProcesses(List<String> commands, String action){
		try {
			ProcessBuilder pb = new ProcessBuilder(commands);
			Process process = pb.start();

			if(action.equals("download")){
				InputStream is = process.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				String line;
				while((line=br.readLine())!= null){
					downloadProgress(line);
				}	
			}
			try{
				process.waitFor();
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public static void main(String[] args){
		Menu menu = new Menu();
		int menuOption = menu.startMenu();
		Yt2mp3 obj = new Yt2mp3();
		//after getting the option of menu
		//input for the actual link needs to be received (not via args)
		//validate links method needs to be run here after the menu not in constructor
		switch(menuOption){
			case 1:
				//run single link
				//validateLink(link);
				////runYTDLP(link);
				System.out.println(menuOption);
				break;
			case 2:
				//run multi links (create method for reading in multiple links)
				System.out.println(menuOption);
				break;
			case 3:
				//run the download history check
				System.out.println(menuOption);
				break;
			default:
				System.exit(0);
		}		
	}
}
