# Amlogic Meson64 Configurations

SOC_FAMILY = "amlogic:meson-gx"
require conf/machine/include/amlogic-meson.inc

XSERVER:append = " xserver-xf86-config"

KERNEL_IMAGETYPE = "Image"

UBOOT_ENTRYPOINT ?= "0x01080000"
UBOOT_LOADADDRESS ?= "0x1080000"
UBOOT_FDT_LOADADDR ?= "0x01000000"
UBOOT_BOOT_CMD ?= "booti"
UBOOT_KERNEL_NAME ?= "Image"

UBOOT_ROOT ?= "mmcblk0p2"
