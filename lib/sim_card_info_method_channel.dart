import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'sim_card_info_platform_interface.dart';

/// An implementation of [SimCardInfoPlatform] that uses method channels.
class MethodChannelSimCardInfo extends SimCardInfoPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('com.miliky/simCard_info');

  @override
  void requestPermission() async {
    await methodChannel.invokeMethod('requestPermission');
  }

  @override
  Future<bool> checkPermission() async => await methodChannel.invokeMethod('checkPermission');

  @override
  Future<String> getSimCardInfo() async => await methodChannel.invokeMethod('getSimCardInfo');
}
