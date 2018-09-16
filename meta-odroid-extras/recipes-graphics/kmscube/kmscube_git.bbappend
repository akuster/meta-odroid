DEPENDS += " ${@bb.utils.contains('MACHINE_FEATURES', 'mali', 'virtual/egl', '', d)}"
