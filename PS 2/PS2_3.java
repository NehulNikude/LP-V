import mpi.MPI;
import java.util.Arrays;

public class PS2_3 {
    public static void main(String[] args) throws Exception {
        MPI.Init(args);

        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();

        final int unitSize = 3;
        final int totalElements = unitSize * size;
        final int root = 0;
        
        int[] sendBuffer = new int[totalElements];
        int[] receiveBuffer = new int[unitSize];

        if (rank == root) {
            for (int i = 0; i < totalElements; i++) {
                sendBuffer[i] = i+1;
            }
            System.out.println("Root Array = " + Arrays.toString(sendBuffer));
        }

        MPI.COMM_WORLD.Scatter(sendBuffer, 0, unitSize, MPI.INT, receiveBuffer, 0, unitSize, MPI.INT, root);

        int localSum = 0;
        for (int x: receiveBuffer) localSum += x;
        double localAvg = localSum/unitSize;

        for (int i = 0; i < size; i++) {
            MPI.COMM_WORLD.Barrier();
            if (rank == i) {
                System.out.println("Process " + rank
                    + " chunk = " + Arrays.toString(receiveBuffer)
                    + " local average = " + localAvg
                );
            }
        }

        double[] reduceSend = { localAvg };
        double[] totalAvg = new double[1];

        MPI.COMM_WORLD.Reduce(reduceSend, 0, totalAvg, 0, 1, MPI.DOUBLE, MPI.SUM, root);

        if (rank == root) System.out.println("Final Average = " + totalAvg[0]/size);

        MPI.Finalize();
    }
}
