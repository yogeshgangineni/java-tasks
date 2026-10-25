class TemperatureMonitoringThread {
    void temperatureMonitoring() {
        System.out.println("Temperature Monitoring Thread is running");
        System.out.println("Getting you the temperature readings");
        int warningTemp = 0;

        for (int i = 18; i <= 35; i++) {
            int randomTemperature = (int) (Math.random() * 31) + 10;
            if (randomTemperature < 18 || randomTemperature > 35) {
                System.out.println("\nALERT: Temperature is outside the normal range: "
                        + randomTemperature + " degree Celsius - Warning");
                warningTemp++;
            } else {
                System.out.println("\nTemperature is within the normal range: "
                        + randomTemperature + " degree Celsius");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }
        System.out.println("Total temperature warnings: " + warningTemp);
    }
}

class HumidityMonitoringThread {
    void humidityMonitoring() {
        System.out.println("Humidity Monitoring Thread is running");
        System.out.println("Getting you the humidity readings");
        int warningHumidity = 0;

        for (int i = 30; i <= 70; i++) {
            int randomHumidity = (int) (Math.random() * 81) + 10;
            if (randomHumidity > 70 || randomHumidity < 30) {
                System.out.println("\nALERT: Humidity is outside the normal range: "
                        + randomHumidity + "% - Warning");
                warningHumidity++;
            } else {
                System.out.println("\nHumidity is within the normal range: "
                        + randomHumidity + "%");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }
        System.out.println("Total humidity warnings: " + warningHumidity);
    }
}

class AirQualityIndexThread {
    void airQualityindexing() {
        System.out.println("Air Quality Index Thread is running");
        int warningAirQuality = 0;

        for (int i = 0; i <= 100; i++) {
            int randomAirQuality = (int) (Math.random() * 301);
            if (randomAirQuality > 200) {
                System.out.println("\nALERT: Air Quality is greater than 200: "
                        + randomAirQuality + " AQI - Critical Warning");
                warningAirQuality++;
            } else if (randomAirQuality > 100) {
                System.out.println("\nALERT: Air Quality is greater than 100: "
                        + randomAirQuality + " AQI - Warning");
                warningAirQuality++;
            } else {
                System.out.println("\nAir Quality is less than or equal to 100: "
                        + randomAirQuality + " AQI");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }
        System.out.println("Total air quality warnings: " + warningAirQuality);
    }
}

class MultiThreadingInProblemStatement {
    public static void main(String[] args) {
        System.out.println("==================Environmental Monitoring System===================");

        TemperatureMonitoringThread temperatureMonitoringThread =
                new TemperatureMonitoringThread();
        HumidityMonitoringThread humidityMonitoringThread =
                new HumidityMonitoringThread();
        AirQualityIndexThread airQualityIndexThread = new AirQualityIndexThread();

        Thread tempThread = new Thread(temperatureMonitoringThread::temperatureMonitoring);
        Thread humidityThread = new Thread(humidityMonitoringThread::humidityMonitoring);
        Thread airQualityThread = new Thread(airQualityIndexThread::airQualityindexing);

        tempThread.start();
        humidityThread.start();
        airQualityThread.start();
    }
}