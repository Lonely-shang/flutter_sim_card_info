import 'package:sim_card_info/data/sim_card.dart';

import 'sim_card_info_platform_interface.dart';

class SimCardInfo {
  static void get requestPermisson {
    SimCardInfoPlatform.instance.requestPermission();
  }

  static Future<bool> get checkPermission async {
    return await SimCardInfoPlatform.instance.checkPermission();
  }

  static Future<List<SimCard>> get getSimCardInfo async {
    List<SimCard> simCardInfo;
    try {
      String jsonStr = await SimCardInfoPlatform.instance.getSimCardInfo();
      simCardInfo = SimCard.parseSimCards(jsonStr);
    } catch (e) {
      simCardInfo = [];
    }
    return simCardInfo;
  }
}
