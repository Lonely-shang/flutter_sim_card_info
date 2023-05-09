import 'dart:convert';

class SimCard {
  int slotIndex = 0;
  String carrierName = "";
  String displayName = "";
  String phoneNumber = "";
  String countryPhonePrefix = "+86";

  SimCard({required this.slotIndex, required this.carrierName, required this.displayName, required this.phoneNumber, required this.countryPhonePrefix});

  static SimCard fromMap(Map<String, dynamic> map) => SimCard(slotIndex: map["slotIndex"], carrierName: map["carrierName"], displayName: map["displayName"], phoneNumber: map["phoneNumber"], countryPhonePrefix: map["countryPhonePrefix"]);

  static List<SimCard> parseSimCards(String jsonStr) => List<SimCard>.from(json.decode(jsonStr).map((item) => SimCard.fromMap(item)));
}
