# yt2mp3

This Java program takes a YouTube video URL link as input, downloads the audio, and converts it to an MP3 file. 
The project utilizes the `yt-dlp` program to download the audio and `ffmpeg` for the conversion to MP3.

## Requirements
- Java 8 or higher
- `yt-dlp` (YouTube downloader)
- `ffmpeg` (for audio conversion)

## Usage
1. Clone this repository:
   ```
   git clone https://github.com/LEQN/yt2mp3.git
   cd ytmp3
   ```
2. Compile the Java program:
   ```
   javac Yt2mp3.java
   ```
3. Run the program:
   ```
   java Yt2mp3 <YouTube URL>
   ```
The downloaded mp3 file will be in the `output` folder, which will be created by the program.

### Example
```
java Yt2mp3 https://www.youtube.com/watch?v=dQw4w9WgXcQ
```

output:
```
Validating link...
Extracting audio...
[download] 100%
Converting to mp3...
Finished! audio in output folder.
```
