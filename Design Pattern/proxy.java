interface VideoDownloader {
    void downloadVideo(String videoUrl);
}

class RealVideoDownloader implements VideoDownloader {  //Real Subject
    @Override
    public void downloadVideo(String videoUrl) {
        System.out.println("Downloading video from: " + videoUrl);
    }
}

class ProxyVideoDownloader implements VideoDownloader {
    private RealVideoDownloader realDownloader;
    private boolean isPremiumUser;

    public ProxyVideoDownloader(boolean isPremiumUser) {
        this.isPremiumUser = isPremiumUser;
    }

    @Override
    public void downloadVideo(String videoUrl) {
        if (!isPremiumUser) {
            System.out.println("Access Denied. Upgrade to premium to download videos.");
            return;
        }

        if (realDownloader == null) {
            realDownloader = new RealVideoDownloader();
        }

        System.out.println("Proxy: Logging download request...");
        realDownloader.downloadVideo(videoUrl);
    }
}

public class proxy {
    public static void main(String[] args) {
        VideoDownloader freeUser = new ProxyVideoDownloader(false);
        freeUser.downloadVideo("http://example.com/video1");

        System.out.println();

        VideoDownloader premiumUser = new ProxyVideoDownloader(true);
        premiumUser.downloadVideo("http://example.com/video2");
    }
}
