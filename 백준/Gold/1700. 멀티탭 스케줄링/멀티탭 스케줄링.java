import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st1.nextToken());
        int k = Integer.parseInt(st1.nextToken());
        
        int[] timeline = new int[k];
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            timeline[i] = Integer.parseInt(st2.nextToken());
        }

        Set<Integer> outlet = new HashSet<>();
        int count = 0;

        for (int i = 0; i < k; i++) {
            int current = timeline[i];

            // 이미 꽂혀 있으면 넘어감
            if (outlet.contains(current)) continue;

            // 멀티탭이 가득 차 있으면 플러그를 뽑아야 함
            if (outlet.size() == n) {
                int deviceToRemove = -1;
                int maxDistance = -1;

                // 현재 멀티탭에 꽂힌 기기들 중 가장 나중에 사용되거나 더 이상 사용되지 않는 기기 선택
                for (int device : outlet) {
                    int nextUsage = Integer.MAX_VALUE;

                    for (int j = i + 1; j < k; j++) {
                        if (timeline[j] == device) {
                            nextUsage = j;
                            break;
                        }
                    }

                    if (nextUsage > maxDistance) {
                        maxDistance = nextUsage;
                        deviceToRemove = device;
                    }
                }

                // 플러그를 뽑음
                outlet.remove(deviceToRemove);
                count++;
            }

            // 새로운 기기를 멀티탭에 꽂음
            outlet.add(current);
        }

        System.out.println(count);
    }
}
