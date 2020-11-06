FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION ?= "4.9.177"
LINUX_VERSION_odroid-c4-hardkernel ?= "4.9.236"

KBRANCH ?= "odroidn2-4.9.y"
KBRANCH_odroid-c4-hardkernel ?= "odroidg12-4.9.y"

SRCREV ?= "c1116eecfb0a085f813ad8925b083e7ffaa7d7d7"
SRCREV_odroid-c4-hardkernel = "800070774d40124984fa6a654854c93c6f78da3c"

KBUILD_DEFCONFIG_odroid-c4-hardkernel = "odroidg12_defconfig"

EXTRA_OEMAKE_append = " KBUILD=${B}"
require linux-hardkernel.inc

KERNEL_EXTRA_FEATURES = ""

O_KERNEL_CONFIG_odroid-n2-hardkernel  = "odroid-n2-hardkernel"

SRC_URI_append_odroid-n2-hardkernel ?= "\
    file://${O_KERNEL_CONFIG}/defconfig \
    file://0001-perf-Make-perf-able-to-build-with-latest-libbfd.patch \
"

do_install_prepend() {
    bbnote "custom kernel_do_install customization"
    cp ${B}/arch/arm64/boot/dts/amlogic/*.dtb ${B}/arch/arm64/boot/dts/
}

COMPATIBLE_MACHINE = "(odroid-n2-hardkernel|odroid-c4-hardkernel)"

