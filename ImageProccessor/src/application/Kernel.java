package application;

/**
 * A class for a Kernel Matrices
 * @author Johnathan Dewey
 * @version September 13th 2023
 */

public class Kernel {

	/**
	 * Attributes
	 */
	public int _radius;
	double [][] _kernel;

	/**
	 * Default Constructor
	 */
	public Kernel() {
		this(3);
	}

	/**
	 * Overloaded Constructor: takes in a radius and initializes the kernel
	 */
	public Kernel(int radius) {
		_radius = radius;

		_kernel = new double[_radius][_radius];
		initialize(_kernel);

	}

	/**
	 * Support Method: initializes kernel to be zero
	 */
	private double[][] initialize(double[][] _arr){
		for (int x = 0; x < _radius; x++) {
			for (int y = 0; y < _radius; y++) {
				_arr[x][y] = 0;
			}
		}
		return _arr;

	}

	/**
	 * Support Method: changes the kernel attribute to another matrix
	 */
	private void changeKernel(double[][] matrix) {
		_kernel = matrix;
		_radius = matrix.length;
	}

	/**
	 * Loader Method: loads the edge detect kernel
	 */
	public void edgeDetect() {
		double[] line1 = {-1,-1,-1};
		double[] line2 = {-1, 8, -1};
		double[] line3 = {-1,-1,-1};
		double[][] edgeDetect = { line1, line2, line3};

		changeKernel(edgeDetect);
	}

	/**
	 * Loader Method: loads the box blur kernel
	 */
	public void boxBlur(int rows, int columns) {
		double[][] blur = new double[rows][columns];
		double coefficent = 1.0 / (rows * columns);
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < columns; col++) {
				blur[row][col] =  coefficent;
			}
		}
		changeKernel(blur);
	}

	/**
	 * Loader Method: loads the gaussian blur kernel
	 */
	public void gaussianBlur(double sigma) {
		double[][] gaussianBlur = new double[3][3];
		double b = 0.0;

		//Normalize (Sigma G(i,j))
		for (int i = 0; i < gaussianBlur.length; i++) {
			for (int j = 0; j < gaussianBlur.length; j++) {
				//System.out.print("Kernel Coordinates: " + (i - 1) + " " + (j - 1));
				//b += G(sigma, i -1 , j - 1);
				b += gaussianEquation(i -1 , j - 1, sigma);
			}
		}

		//Apply G(i, j)/B
		for (int x = 0; x < gaussianBlur.length; x++) {
			for (int y = 0; y < gaussianBlur.length; y++) {
				double product = gaussianEquation(x - 1 , y - 1, sigma) / b;
				gaussianBlur[x][y] = product;
			}
		}

		changeKernel(gaussianBlur);
	}

	private double gaussianEquation(int x, int y, double sigma) {
		double exponent = -1 *((Math.pow(x, 2) + Math.pow(y, 2))/(2 * Math.pow(sigma, 2)));
		double product = (1 / ( 2 * Math.PI * Math.pow(sigma, 2))) * Math.pow(Math.E, exponent);

		//double exponent;
		//double 

		return product;
		//return G(sigma, x, y);
	}
}
