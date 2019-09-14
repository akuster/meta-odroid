DESCRIPTON = "Emacs Without X for odroid-c2"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

PR = "r0"

SRC_URI = "file://odroid-c2-emacs.tar.gz"

S = "${WORKDIR}"
FILES_${PN}="${prefix}"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"
PACKAGES= "${PN}"

PR = "r0"

RDEPENDS_${PN} = "bash perl cairo gtk+ libxft libsm libpng libice alsa-lib tiff libxml2 gtk+3"

do_install() {

    # ${D} is effectively the root directory of the target system.
    install -d ${D}${prefix}/
  
    #
    # Install files in to the image
    #
    # The files fetched via SRC_URI (above) will be in ${WORKDIR}.
    #
    #tar zxvf ${WORKDIR}/odroid-c2-emacs  ${D}${prefix}/
    cp -r ${WORKDIR}/local ${D}${prefix}/
}



