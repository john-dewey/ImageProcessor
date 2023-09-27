package application;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;

/**
 * A class for a basic editable Picture
 * @author Andrea Tartaro
 * @author Johnathan Dewey
 * @version September 13th 2023
 */

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public class Picture extends Canvas {

	/**
	 * Attributes
	 */
	public WritableImage img;
	public int width, height;


	/**
	 * Default Constructor
	 */
	public Picture() {
		this(getPlainImage(Constants.COLOR_BACKGROUND, Constants.MAX_IMG_WIDTH, Constants.MAX_IMG_HEIGHT));
	}

	/**
	 * Overloaded Constructor: Takes in a filename and creates a picture from the path
	 */
	public Picture(String filename) {
		Image verifiedImage;
		Image initialImage = new Image(filename);

		//if the image is going to be too big for the program, resize it
		if (initialImage.getWidth() > Constants.MAX_IMG_WIDTH || initialImage.getHeight() > Constants.MAX_IMG_HEIGHT) {
			verifiedImage = new Image(filename,Constants.MAX_IMG_WIDTH, Constants.MAX_IMG_HEIGHT, Constants.ENABLE_PRESERVE_RATIO ,Constants.ENABLE_SMOOTHING);
		}
		//otherwise keep it
		else {
			verifiedImage = new Image(filename);
		}

		this.width = (int)verifiedImage.getWidth();
		this.height = (int)verifiedImage.getHeight();
		this.img = new WritableImage(verifiedImage.getPixelReader(), this.width, this.height);

		this.setWidth(this.width);
		this.setHeight(this.height);
	}

	/**
	 * Overloaded Constructor: Takes in a image and creates a picture
	 */
	public Picture(Image image) {
		this.width = (int)image.getWidth();
		this.height = (int)image.getHeight();
		this.img = new WritableImage(image.getPixelReader(), this.width, this.height);

		this.setWidth(this.width);
		this.setHeight(this.height);
	}

	/**
	 * Overloaded Constructor: Takes in a picture and creates a picture
	 */
	public Picture(Picture picture) {
		this.width = (int)picture.getWidth();
		this.height = (int)picture.getHeight();
		this.img = new WritableImage(picture.img.getPixelReader(), this.width, this.height);

		this.setWidth(this.width);
		this.setHeight(this.height);
	}

	/**
	 * Changes the image of this Picture to another picture
	 */
	public void setPicture(Picture that) {

		this.width = (int)that.getWidth();
		this.height = (int)that.getHeight();
		this.img = new WritableImage(that.img.getPixelReader(), this.width, this.height);

		this.setWidth(this.width);
		this.setHeight(this.height);

		this.draw();
	}

	public void export(String filename) {
		String fileFormat = "src/out/%s.png";
		File file = new File(String.format(fileFormat, filename));

		RenderedImage renderedImage = SwingFXUtils.fromFXImage(this.img, null);
		try {
			ImageIO.write(
					renderedImage, 
					"png",
					file);
		} catch (IOException e) {

		}
	}


	/**
	 * Utility Method: Draws the image
	 */
	public void draw() {
		this.clear();    
		GraphicsContext gc = this.getGraphicsContext2D();  
		gc.drawImage(img, 0, 0);
	}

	/**
	 * Utility Method: Clears the image
	 */
	private void clear() {
		GraphicsContext gc = this.getGraphicsContext2D();
		gc.setFill(Constants.COLOR_WHITE);
		gc.fillRect(0, 0, this.width, this.height);
	}

	/**
	 * Utility Method: Copies this image into a new one
	 */
	public Picture copy() {
		return new Picture(this);
	}

	/**
	 * Multimedia Effect Method: Applies a composite effect using another picture and a mask on this image
	 */
	public void doComposite(Picture foreground, Picture mask) {

		mask.doThreshold(Constants.MATH_THRESHOLD_DEFAULT);

		int terminalA = Math.min(Math.min(this.width, foreground.width), mask.width);
		int terminalB = Math.min(Math.min(this.height, foreground.height), mask.height);

		for (int x = 0; x < terminalA ; x++) {
			for (int y = 0; y < terminalB ; y++) {
				//Pixel initial_p is used to read from and write to this picture
				Pixel backgroundP = this.getPixel(x, y);

				//Pixel composite_P is used to read from the interpolating image
				Pixel foregroundP = foreground.getPixel(x, y);

				//Pixel mask_P is used to read from the interpolating image
				Pixel maskP = mask.getPixel(x, y);

				backgroundP.composite(foregroundP, maskP);
			}
		}
		this.draw();
	}

	/**
	 * Multimedia Effect Method: Applies a convolution effect with a kernel with specified weights on this image
	 */
	public void doConvolution(Kernel kernel) {
		Picture result = new Picture(this);

		int xMax = this.width;
		int yMax = this.height;
		for (int x = 0; x < xMax; x++) {
			for (int y = 0; y < yMax; y++) {

				Pixel XY = result.getPixel(x, y);

				double [] accumulator = {0,0,0};
				XY.convolution(accumulator, kernel, this, x, y);
			}
		}
		setPicture(result);
		this.draw();
	}

	/**
	 * Multimedia Effect Method: Applies a interpolation/extrapolation effect  with another image on this image
	 */
	public void doPolation(Picture importedPicture, Double alpha) {
		//Make it so the function can only work on areas of the image that overlap
		int terminalA = Math.min(this.width, importedPicture.width);
		int terminalB = Math.min(this.height, importedPicture.height);

		for (int x = 0; x < terminalA ; x++) {
			for (int y = 0; y < terminalB ; y++) {
				//Pixel initial is used to read from and write to this picture
				Pixel initial = this.getPixel(x, y);

				//Pixel imported is used to read from the interpolating image
				Pixel imported = importedPicture.getPixel(x, y);

				initial.polation(imported, alpha);
			}
		}
		this.draw();
	}

	/**
	 * Effect Method: Applies a box blur effect on this image
	 */
	public void doBlur() {
		Kernel blur = new Kernel();
		blur.boxBlur(3, 3);
		doConvolution(blur);
	}

	/**
	 * Effect Method: Applies a edge detect effect on this image
	 */
	public void doEdge() {
		Kernel edgeDetect = new Kernel();
		edgeDetect.edgeDetect();
		doConvolution(edgeDetect);
	}

	/**
	 * Effect Method: Applies a brighten effect on this image
	 */
	public void doBrighten(Double alpha) {
		//create a plain white image
		Picture white = new Picture(getPlainImage(Constants.COLOR_WHITE, this.width, this.height));

		this.doPolation(white, alpha);
	}

	/**
	 * Effect Method: Applies a darken effect on this image
	 */
	public void doDarken(Double alpha) {
		//create a plain black image
		Picture black = new Picture(getPlainImage(Constants.COLOR_BLACK, this.width, this.height));

		this.doPolation(black, alpha);
	}

	/**
	 * Effect Method: Applies a greyscale effect on this image
	 **/
	public void doGreyscale() {
		for (int y = 0; y < this.height; y++) {
			for (int x = 0; x < this.width; x++) {
				Pixel p = this.getPixel(x, y);
				p.greyscale();
			}
		}
		this.draw();
	}

	/**
	 * Effect Method: Applies a darken effect on this image
	 */
	public void doNoise(Double alpha) {
		//create a noise map
		Picture noiseMap = new Picture(getNoiseMap(this.width, this.height));

		this.doPolation(noiseMap, alpha);

	}

	/**
	 * Effect Method: Applies a threshold effect on this image
	 **/
	public void doThreshold(double threshholdLimit){
		for (int x = 0; x < this.width; x++) {
			for (int y = 0; y < this.height; y++) {
				Pixel p = this.getPixel(x, y);

				int red = p.getRed();
				int green = p.getGreen();
				int blue = p.getBlue();

				double average = ((red + green + blue)/3)/255.0;
				p.threshold(average, threshholdLimit);
			}
		}
		this.draw();
	}

	/**
	 * Effect Method: Applies a limited channel level effect on this image
	 **/
	public void doQuantization(int level) {

		double[] levels = new double[level + 1];
		for (int i = 0; i < levels.length; i++) {
			levels[i] = i;
		}

		double[] outputLevels = getOutputLevels(levels, level);
		double[] thresholdLevels = getThresholds(levels, level);

		for (int x = 0; x < this.width ; x++) {
			for (int y = 0; y < this.height ; y++) {

				Pixel initial = this.getPixel(x, y);

				initial.quantization(level, thresholdLevels, outputLevels);
			}
		}
		this.draw();
	}

	public void doGaussian(double sigma) {
		Kernel gaussian = new Kernel();
		gaussian.gaussianBlur(sigma);
		doConvolution(gaussian);
	}

	public void doScale(double percent) {
		double newWidth = img.getWidth() * percent;
		double newLength = img.getHeight() * percent;

		export("temp");

		Image i = new Image(Constants.TEXT_TEMP_FILE, newWidth, newLength, Constants.ENABLE_PRESERVE_RATIO, Constants.ENABLE_SMOOTHING);
		setPicture(new Picture(i));

	}

	public void doRotate(int x, int y, double degrees) {
		int [] arbitraryRotationPoint = {x,y};
		Picture editCanvas = Picture.getPlainImage(Constants.COLOR_BLACK, this.width, this.height);

		for (int i = 0; i < editCanvas.width ; i++) {
			for (int j = 0; j < editCanvas.height ; j++) {

				Pixel edit = editCanvas.getPixel(i, j);

				int [] newPoint = getOtherPoint(arbitraryRotationPoint, new int[] {i,j}, degrees);

				//ignore if not a valid spot
				if (newPoint[0] < 0 || newPoint[1] < 0) {
				}
				else {
					Pixel extract = this.getPixel(newPoint[0], newPoint[1]);
					edit.setColor(extract.getColor());
				}			
			}
		}
		setPicture(editCanvas);
		this.draw();
	}

	private int[] getOtherPoint(int[] arbitraryRotationPoint, int[] currentPoint, double angle) {

		double degree = angle * (Math.PI/180.0);

		double Xo = arbitraryRotationPoint[0];
		double Xi = currentPoint[0];

		double Yo = arbitraryRotationPoint[1];
		double Yi = currentPoint[1];


		double Xproduct = Xo +(Math.cos(degree)*(Xi-Xo)) - (Math.sin(degree)*(Yi - Yo));
		double Yproduct = Yo +(Math.sin(degree)*(Xi-Xo)) + (Math.cos(degree)*(Yi - Yo));

		return new int[] {(int)Xproduct,(int)Yproduct};
	}

	/**
	 * Support Method: calculates the thresholds based inputted levels
	 */
	private double[] getThresholds(double[] levels, int level) {
		int size = level;
		double[] returnArray = new double[size];
		for (int i = 1; i < size; i++) {
			returnArray[i] = ((2 * levels[i]) - 1) / (2 * (level - 1));
		}
		return returnArray;
	}

	/**
	 * Support Method: calculates the output levels based inputted levels
	 */
	private double[] getOutputLevels(double[] levels, int level) {
		int size = level;
		double[] returnArray = new double[size];
		for (int i = 0; i < size; i++) {
			returnArray[i] = levels[i] / (level - 1);
		}
		return returnArray;
	}

	/**
	 * Utility Method: Gets the pixel object for a individual coordinate
	 */
	public Pixel getPixel(int x, int y) {
		return new Pixel(this.img, x, y);
	}

	public static Picture getPlainImage(Color color, int width, int length) {
		WritableImage blankImage = new WritableImage(width, length);
		Picture canvas = new Picture(blankImage);


		for (int x = 0; x < canvas.width; x++) {
			for (int y = 0; y < canvas.height; y++) {
				Pixel p = canvas.getPixel(x, y);
				p.setColor(color);
			}
		}
		return canvas;
	}

	public static Picture getNoiseMap(int width, int length) {
		WritableImage blankImage = new WritableImage(width, length);
		Picture canvas = new Picture(blankImage);

		for (int x = 0; x < canvas.width; x++) {
			for (int y = 0; y < canvas.height; y++) {
				Pixel p = canvas.getPixel(x, y);
				p.setColor(Color.rgb(Pixel.getRandomRBG(), Pixel.getRandomRBG(), Pixel.getRandomRBG()));
			}
		}
		return canvas;
	}
}