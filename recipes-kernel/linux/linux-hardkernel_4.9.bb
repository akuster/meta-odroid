FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION ?= "4.9.177"
LINUX_VERSION_odroid-c4-hardkernel ?= "4.9.218"

KBRANCH ?= "odroidn2-4.9.y"
KBRANCH_odroid-c4-hardkernel ?= "odroidg12-4.9.y"

SRCREV ?= "c1116eecfb0a085f813ad8925b083e7ffaa7d7d7"
SRCREV_odroid-c4-hardkernel = "46c52cc30d8f7631aeddf5266e93a8e9f9077deb"

KBUILD_DEFCONFIG_odroid-c4-hardkernel = "odroidg12_defconfig"

require linux-hardkernel.inc

KERNEL_EXTRA_FEATURES = ""

O_KERNEL_CONFIG_odroid-n2-hardkernel  = "odroid-n2-hardkernel"


SRC_URI_append_odroid-n2-hardkernel ?= "file://${O_KERNEL_CONFIG}/defconfig"

SRC_URI_append_odroid-c4-hardkernel = "\
    file://0001-Disable-warnings-found-with-gcc9.patch \
    file://0001-ODROID-C4-Enable-LCD-and-Touchscreen.patch \
"

do_install_prepend() {
    bbnote "custom kernel_do_install customization"
    cp ${B}/arch/arm64/boot/dts/amlogic/*.dtb ${B}/arch/arm64/boot/dts/
}

COMPATIBLE_MACHINE = "(odroid-n2-hardkernel|odroid-c4-hardkernel)"

