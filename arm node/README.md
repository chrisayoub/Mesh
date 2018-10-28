# Arm Node

First run `interfaces.sh` to properly assign interface names based on MAC.

Run `wlan1_ap.sh` to make `wlan1` a new AP which forwards packets with interface `wlan0`.

Weird routing issues currently require the script `fix_routing.sh` to fix the configuration. You can see this with `route -n`.

If the `hostapd` does not come up, try killing `wpa_supplicant` for `wlan1` and reconnecting `wlan0` to CentralNode.

