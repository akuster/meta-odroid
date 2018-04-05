# Odroid BSP default settings

PREFERRED_PROVIDER_virtual/xserver ?= "xserver-xorg"
XSERVER ?= "xserver-xorg \
           xf86-input-evdev \
           xf86-input-mouse \
           xf86-video-fbdev \
           xf86-input-keyboard"

PREFERRED_PROVIDER_virtual/kernel ?= "linux-stable"
PREFERRED_VERSION_linux-stable ?= "4.14%"
LINUX_VERSION_EXTENSION ?= "-odroid"

KERNEL_IMAGETYPE ?= "zImage"

PREFERRED_PROVIDER_virtual/bootloader ?= "u-boot"
PREFERRED_PROVIDER_u-boot ?= "u-boot"
PREFERRED_VERSION_u-boot ?= "2018.01"

USE_BOOTSRC ?= "1"
UBOOT_ENV  ?= "boot"
UBOOT_BINARY ?= "u-boot-dtb.bin"
UBOOT_ENV_SUFFIX ?= "scr"
UBOOT_SCRIPT ?= "boot.${UBOOT_ENV_SUFFIX}"
UBOOT_LOAD_CMD ?= "fatload"
UBOOT_BOOT_CMD ?= "bootz"

UBOOT_BOOTDEV  ?= "0"
UBOOT_BOOTPART ?= "1"
UBOOT_ROOTDEV ?= ""
UBOOT_ROOTPART ?= "2"
UBOOT_BOOTTYPE ?= "mmc"
UBOOT_ROOTTYPE ?= "mmcblk1p"

UBOOT_EXTRA_ENV ?= ""
UBOOT_FILE_TITLE ?= "#"

BOOT_PREFIX ?= ""

IMAGE_ROOTFS_ALIGNMENT ?= "4096"

MACHINE_FEATURES = "serial usbgadget usbhost vfat ext2 alsa screen"
MACHINE_EXTRA_RRECOMMENDS += " kernel-modules kernel-devicetree"

EXTRA_IMAGEDEPENDS += "u-boot"

IMAGE_FSTYPES += " ext4 sdcard"
IMAGE_CLASSES += "image_types_odroid"
