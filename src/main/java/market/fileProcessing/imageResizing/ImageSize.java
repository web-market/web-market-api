package market.fileProcessing.imageResizing;

public enum ImageSize {
    LOW_RESOLUTION(100, 135),
    MEDIUM_RESOLUTION(300, 400),
    HIGH_RESOLUTION(1000, 1335);

    private final int width;
    private final int height;

    ImageSize (final int width, final int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
}
