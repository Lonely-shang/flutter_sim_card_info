import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:sim_card_info/sim_card_info_method_channel.dart';

void main() {
  MethodChannelSimCardInfo platform = MethodChannelSimCardInfo();
  const MethodChannel channel = MethodChannel('sim_card_info');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    // expect(await platform.getPlatformVersion(), '42');
  });
}
