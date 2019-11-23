# Copyright (C) 2018 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Mali Samples"
HOMEPAGE = "http://openlinux.amlogic.com:8000/download/GPL_code_release/ThirdParty/?C=M;O=D"
LICENSE = "Closed"
LIC_FILES_CHKSUM = "file://Mali_OpenGL_ES_SDK_v2.4.4_Documentation.html;md5=561d0b7167db0f2048c175658731c52e"
SECTION = "apps"
DEPENDS = "virtual/egl"

GITPV = ".71fdbd"

SRC_URI = "http://openlinux.amlogic.com:8000/download/GPL_code_release/ThirdParty/Mali_OpenGL_ES_SDK_v${PV}${GITPV}_Linux_x86.tar.gz"
SRC_URI[md5sum] = "441f89136e4fc1137214610aff0f7021"
SRC_URI[sha256sum] = "8ec00f3fa38b4af9b07b8a1d6ce0d68bda8124279586f5087acb24c658307b65"

inherit cmake pkgconfig features_check
# depends on virtual/egl
REQUIRED_DISTRO_FEATURES = "opengl x11"

export TOOLCHAIN_ROOT = "${HOST_PREFIX}"
EXTRA_OECMAKE = "-DTARGET=arm"

S = "${WORKDIR}/Mali_OpenGL_ES_SDK_v${PV}"

do_install_append() {
	install -D -m 0755 ${B}/simple_framework/libsimple_framework2.so ${d}${libidr}
	install -D -m 0755 ${B}/simple_framework/libsimple_framework3.so ${D}${libdir}
}

COMPATIBLE_MACHINE = "(odroid-c2|odroid-xu3|odroid-xu4|odroid-xu3-lite)"
