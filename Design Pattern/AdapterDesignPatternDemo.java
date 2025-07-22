// Target Interface
interface MediaPlayer {
    void play(String audioType, String fileName);
}

// Adaptee
class AdvancedMediaPlayer {
    public void playVlc(String fileName) {
        System.out.println("Playing VLC: " + fileName);
    }

    public void playMp4(String fileName) {
        System.out.println("Playing MP4: " + fileName);
    }
}

// Adapter
class MediaAdapter implements MediaPlayer {
    AdvancedMediaPlayer advancedPlayer = new AdvancedMediaPlayer();

    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("vlc")) {
            advancedPlayer.playVlc(fileName);
        } else if (audioType.equalsIgnoreCase("mp4")) {
            advancedPlayer.playMp4(fileName);
        }
    }
}

// Client
class AudioPlayer implements MediaPlayer {
    MediaAdapter adapter;

    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("mp3")) {
            System.out.println("Playing MP3: " + fileName);
        } else {
            adapter = new MediaAdapter();
            adapter.play(audioType, fileName);
        }
    }
}
public class AdapterDesignPatternDemo {
    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();
        
        audioPlayer.play("mp3", "song.mp3");
        audioPlayer.play("vlc", "movie.vlc");
        audioPlayer.play("mp4", "video.mp4");
        audioPlayer.play("avi", "clip.avi"); // This will not be handled by the adapter
    }
}

