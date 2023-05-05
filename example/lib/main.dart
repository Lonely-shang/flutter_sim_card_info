import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:sim_card_info/data/SimCard.dart';
import 'package:sim_card_info/sim_card_info.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({super.key});

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  SimCard? simCard1 = null;
  SimCard? simCard2 = null;

  @override
  void initState() {
    super.initState();
    SimCardInfo.requestPermisson;
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  const Text("sim1Info:"),
                  Text(simCard1!.phoneNumber)
                ],
              ),
              Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  const Text("sim2Info:"),
                  Text(simCard2!.phoneNumber)
                ],
              ),
              MaterialButton(onPressed: getSimCardInfo, child: const Text("click button get simCard")),
            ],
          ),
        ),
      ),
    );
  }

  void getSimCardInfo() async {
    List<SimCard> cardList = await SimCardInfo.getSimCardInfo;
    if (cardList.isNotEmpty) {
      if (cardList.length == 2) {
        setState(() {
          simCard1 = cardList[0];
          simCard2 = cardList[1];
        });
      } else {
        setState(() {
          simCard1 = cardList[0];
        });
      }
    }
  }
}
