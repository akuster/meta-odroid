FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION ?= "4.9.170"
KBRANCH ?= "odroidn2-4.9.y"
SRCREV ?= "4.9.170-27"

require linux-hardkernel.inc

O_KERNEL_CONFIG_odroid-n2-hardkernel  = "odroid-n2-hardkernel"

SRC_URI += "file://${O_KERNEL_CONFIG}/defconfig"


do_install_prepend() {
    bbnote "custom kernel_do_install customization"
    cp ${B}/arch/arm64/boot/dts/amlogic/*.dtb ${B}/arch/arm64/boot/dts/
}

COMPATIBLE_MACHINE = "(odroid-n2-hardkernel)"

