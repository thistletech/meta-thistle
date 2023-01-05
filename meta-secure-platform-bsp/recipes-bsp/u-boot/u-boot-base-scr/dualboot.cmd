# temporarily hijhack kernel memory spot
setexpr baseaddr     ${kernel_addr_r}
setexpr magic_top    ${baseaddr} + 0x00
setexpr version      ${baseaddr} + 0x04
setexpr booting      ${baseaddr} + 0x06
setexpr dirty_boot   ${baseaddr} + 0x08
setexpr boot_counter ${baseaddr} + 0x0a
setexpr boot_limit   ${baseaddr} + 0x0c
setexpr magic_bot    ${baseaddr} + 0x10

setenv filename "thistleboot.data"
setenv state_size 16

setenv version_tag 0xca
setenv magic_top_cst 0x73696874 # this
setenv magic_bot_cst 0x656C7473 # stle

echo;
echo;
echo "~~~~~~~ thistle boot ~~~~~~~";

# clear memory & load state
echo "> reading data from disk..."
mw.b ${baseaddr} 0 ${state_size}
fatload ${devtype} ${devnum}:${distro_bootpart} ${baseaddr} ${filename} ${state_size}

# init if no data on disk (both magic)
if itest *${magic_top} -ne ${magic_top_cst}; then
  echo "! no data on disk, init";
  mw.b ${version} ${version_tag};
  mw.b ${boot_counter} 0;
  mw.b ${boot_limit} 0x03;
  mw.b ${booting} 0x0a;
  mw.b ${dirty_boot} 0x00;
  mw.l ${magic_top} ${magic_top_cst};
  mw.l ${magic_bot} ${magic_bot_cst};
fi
if itest *${magic_bot} -ne ${magic_bot_cst}; then
  echo "! no data on disk, init";
  mw.b ${version} ${version_tag};
  mw.b ${boot_counter} 0;
  mw.b ${boot_limit} 0x03;
  mw.b ${booting} 0x0a;
  mw.b ${dirty_boot} 0x00;
  mw.l ${magic_top} ${magic_top_cst};
  mw.l ${magic_bot} ${magic_bot_cst};
fi

setexpr.b tmp_a *${version}
echo "  version: ${tmp_a}"

setexpr.b tmp_b *${booting}
echo "  currently booting:   ${tmp_b}"

setexpr.b tmp_c *${boot_counter}
setexpr.b tmp_d *${boot_limit}
echo "  boot counter: ${tmp_c}/${tmp_d}"
echo

# switch boot cmd if boot count exceeds limit
if itest.b *${boot_counter} -ge *${boot_limit}; then
  echo "! boot limit exceeded, fallback"

  # swap boot dest
  if itest.b *${booting} -eq 0x0a; then
    mw.b ${booting} 0x0b
  else
    mw.b ${booting} 0x0a
  fi

  mw.b ${dirty_boot} 0x0f
fi

# increment boot counter
setexpr.b tmp_x *${boot_counter} + 1
mw.b ${boot_counter} ${tmp_x}

# store vals
echo "> storing boot values..."
fatwrite ${devtype} ${devnum}:${distro_bootpart} ${baseaddr} ${filename} ${state_size}

# boot
if itest.b *${booting} -eq 0x0a; then
  echo "> booting A"; echo; echo;
  run boot_cmd_a
else
  echo "> booting B"; echo; echo;
  run boot_cmd_b
fi

# unreachable ?
reset