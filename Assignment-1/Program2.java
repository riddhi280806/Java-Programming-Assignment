public class Matrix {
    private final int rows;
    private final int cols;
    private final double[][] data;

    /**
     * Constructor to initialize the matrix.
     * Takes a 2D double array and creates a deep copy.
     */
    public Matrix(double[][] inputData) {
        if (inputData == null || inputData.length == 0) {
            throw new IllegalArgumentException("Matrix data cannot be empty or null.");
        }

        this.rows = inputData.length;
        this.cols = inputData[0].length;
        this.data = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            if (inputData[i].length != cols) {
                throw new IllegalArgumentException("All rows must have the same number of columns.");
            }
            for (int j = 0; j < cols; j++) {
                this.data[i][j] = inputData[i][j];
            }
        }
    }

    /**
     * Returns a new Matrix that is the transpose of the current one.
     */
    public Matrix transpose() {
        double[][] transposedData = new double[this.cols][this.rows];

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                transposedData[j][i] = this.data[i][j];
            }
        }

        return new Matrix(transposedData);
    }

    /**
     * Multiplies this matrix by another matrix.
     */
    public Matrix multiply(Matrix other) {
        // Dimension check: (m x n) * (n x p)
        if (this.cols != other.rows) {
            throw new IllegalArgumentException(
                "Cannot multiply: Columns of the first matrix (" + this.cols + 
                ") must match rows of the second matrix (" + other.rows + ")."
            );
        }

        double[][] resultData = new double[this.rows][other.cols];

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < other.cols; j++) {
                for (int k = 0; k < this.cols; k++) {
                    resultData[i][j] += this.data[i][k] * other.data[k][j];
                }
            }
        }

        return new Matrix(resultData);
    }

    /**
     * Formats the matrix for printing.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sb.append(String.format("%8.2f", data[i][j])).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
