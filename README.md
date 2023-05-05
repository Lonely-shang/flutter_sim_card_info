# sim_card_info

Flutter SimCardInfo for Android API 23+.
### Installation

```
flutter pub add sim_card_info
or
dart pub add sim_card_info
```

# API
### hasPermission

```dart
  bool permisson = await SimCardInfo.hasPermission;
```

### requestPermission
```dart
  await SimCardInfo.requestPermission;
```

### getSimCardInfo

```dart
  List<SimCard> simCardList = await SimCardInfo.getSimCardInfo;
  if (!simCardList.isNotEmpty) {
    TODO("Traverse simCardList to get sim card information")
  } else {
    TODO("No phone number on sim card")
  }
```


