DESCRIPTION = "QT5 based demo apps group"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit packagegroup

RDEPENDS_${PN} = " \
	cinematicexperience \
	qt5-demo-extrafiles \
	qt5everywheredemo \
	qt5nmapcarousedemo \
	qt5ledscreen \
	qt5nmapper \
	qtsmarthome \
	quitbattery \
	quitindicators \
"
