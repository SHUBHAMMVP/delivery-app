# MIH Delivery Android App

A Jetpack Compose starter app for booking NCR product deliveries. The customer booking journey includes:

- Pick-up and drop-off addresses
- Item type and shipment weight (from 1 gram up to 20 tonnes)
- Optional insurance
- Mandatory acknowledgement for prohibited goods, labelling, and authorised alcohol/cigarette limits
- Delivery request confirmation with a tracking identifier

## Run locally

Use JDK 17+ and an Android SDK with API 35 installed:

```bash
./gradlew :app:assembleDebug
```

Open the project in Android Studio to run it on an emulator or device.
