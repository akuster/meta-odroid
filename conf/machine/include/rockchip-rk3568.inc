# Rockchip rk3568 config

SOC_FAMILY = "rockchip:rk3568"
require conf/machine/include/soc-family.inc

SERIAL_CONSOLES ?= "1500000;ttyS2"
UBOOT_CONSOLE ?= "console=ttyS2,1500000 earlycon"

KERNEL_IMAGETYPE = "Image.gz"

UBOOT_ENTRYPOINT ?= "0x00A80000"
UBOOT_LOADADDRESS ?= "0x00A80000"
UBOOT_FDT_LOADADDR ?= "0x0A100000"
UBOOT_BOOT_CMD ?= "booti"
UBOOT_KERNEL_NAME ?= "Image.gz"
