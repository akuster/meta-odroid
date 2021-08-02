# Odroid BSP Arm default settings
require conf/machine/include/odroid-default-settings.inc

def make_dtb_boot_files(d):
    # Generate IMAGE_BOOT_FILES entries for device tree files listed in
    # KERNEL_DEVICETREE.
    alldtbs = d.getVar('KERNEL_DEVICETREE')

    def transform(dtb):
        base = os.path.basename(dtb)
        if dtb.endswith('dtb'):
            # eg: whatever/meson64_odroidc4.dtb has:
            #     DEPLOYDIR file: meson64_odroidc4.dtb
            #     destination: meson64_odroidc4.dtb
            return base
        elif dtb.endswith('dtbo'):
            # overlay dtb:
            # eg: amlogic/overlays/odroidc4/spi0.dtbo has:
            #     DEPLOYDIR file: spi0.dtbo
            #     destination: amlogic/overlays/odroidc4/spi0.dtbo
            return '{};{}'.format(base, dtb)

    return ' '.join([transform(dtb) for dtb in alldtbs.split(' ') if dtb])

XSERVER:append = " \
    ${@bb.utils.contains('MACHINE_FEATURES', 'mali', 'xserver-xorg-module-exa', '', d)} \
"

KERNEL_IMAGETYPE ?= "zImage"

PREFERRED_PROVIDER_virtual/bootloader ?= "u-boot"
PREFERRED_PROVIDER_u-boot ?= "u-boot"
PREFERRED_VERSION_u-boot ?= "202%"

USE_BOOTSRC ?= "1"
UBOOT_ENV  ?= "boot"
UBOOT_BINARY ?= "u-boot-dtb.bin"
UBOOT_ENV_SUFFIX ?= "scr"
UBOOT_SCRIPT ?= "boot.${UBOOT_ENV_SUFFIX}"
UBOOT_LOAD_CMD ?= "ext4load"
UBOOT_BOOT_CMD ?= "bootz"

UBOOT_BOOTPART ?= "2"
UBOOT_ROOTDEV ?= "1"
UBOOT_ROOTPART ?= "2"
UBOOT_BOOTTYPE ?= "mmc"
BOOT_PREFIX ?= "boot/"

UBOOT_EXTRA_ENV ?= ""
UBOOT_FILE_TITLE ?= "#"
BOOT_PREFIX ?= ""
UBOOT_VIDEO ?= "${@bb.utils.contains("DISTRO_FEATURES", "x11", "drm.edid_firmware=edid/1024x768.bin", " ", d)}"
UBOOT_XTRA_CMDLINE ?=  "${@bb.utils.contains('DISTRO_FEATURES', 'TS_vu7+', 'usbhid.quirks=0x16B4:0x0703:0x00000040 ', '', d)}"

IMAGE_BOOT_FILES ?= "${UBOOT_SCRIPT}"

MACHINE_FEATURES_BACKFILL_CONSIDERED = "rtc"

EXTRA_IMAGEDEPENDS:append = " u-boot secure-odroid"
EXTRA_IMAGEDEPENDS:append = " ${@bb.utils.contains("DISTRO_FEATURES", "x11", " odroid-edid", " ", d)}"
IMAGE_INSTALL:append = " kernel-devicetree"

IMAGE_CLASSES += "image_types_odroid"
