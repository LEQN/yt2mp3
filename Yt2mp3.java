class Yt2mp3{

	public Yt2mp3(String link){
		System.out.println("Validating link...");
		validateLink(link);
		System.out.println("Converting youtube link: "+ link);
	}

	private void validateLink(String inputLink){
		String pattern = "^(http(s)?:\\/\\/)?((w){3}.)?youtu(be|.be)?(\\.com)?\\/.+";
		if(!inputLink.isEmpty() && inputLink.matches(pattern)){
			System.out.println("Link is valid");
		}else{
			System.out.println("not a youtube link with valid format.");
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
