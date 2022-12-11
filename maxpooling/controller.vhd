library ieee;
use ieee.std_logic_1164.all;
use ieee.std_logic_unsigned.all;

entity controller is 
port(
	clk,reset:in std_logic;
	start :in std_logic;
	we_a,we_c: out std_logic;
	re_a,re_c: out std_logic;
	ld_m,ld_n,ld_i,ld_j,ld_index: out std_logic;
	en_m,en_n,en_i,en_j,en_index: out std_logic;
	za,zm,zn,zi,zj: in std_logic;
	sel_0,sel: out std_logic;
	sel_max:in std_logic;
	done: out std_logic
);
end controller;

architecture bev of controller is 
type State_type is (s0,s1,s2,s21,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15);
signal state: State_type;
begin
--state process
FSM : process(clk,reset)
 begin
	if reset='1' then state<=s0;
	elsif (clk'event and clk='1') then 
	 case state is 
		when s0 => state <= s1;
		when s1 => 
			if start='1' then state <= s21;
			else state <= s1;
			end if;
		when s21 => 
			if za='1' then state <= s2;
			else state <= s21;
			end if;
		when s2 => 
			if zm='0' then state <= s3;
			else state <= s14;
			end if;
		when s3 =>
			if zn='0' then state <= s4;
			else state <= s13;
			end if;	
		when s4 =>
			 if zi='0' then state <= s5;
			else state <= s11;
			end if;	
		when s5 => 
			if zj='0' then state <= s6;
			else state <= s10;
			end if;
		when s6 =>
			 state <= s7;		
		when s7 =>
			--state<=s8;
			if(sel_max ='1')then state<=s8;
			else state<=s9;
			end if;
		when s8 => state<=s9;
		when s9 => state<=s5;
		when s10=>state <=s4;
		when s11=>state<=s12;
		when s12=>state<=s3;
		when s13=> state<=s2;
		when s14 => state <= s15;
		when s15 => 
			if start='0' then state <= s0;
			else state <= s15;
			end if;
		when others => 
			 state <= s0;
	 end case;
		
	end if;
 end process;

--combinational logic 
we_a <='1' when state = s21 else  '0';
--we_k <='1' when state = s22 else  '0';
re_a <='1' when state = s6 else  '0';
--re_k <='1' when state = s6 else  '0';
re_c <='1' when (state = s6 ) else '0';
we_c <='1' when (state = s8 or state = s12) else '0';
en_m <='1' when state = s13 else '0';
en_n <='1' when state = s11 else '0';
en_index <='1' when state = s11 else '0';
en_i <='1' when state = s10 else '0';
en_j <='1' when state = s9 else '0';
ld_m <='1' when (state = s0) else '0';
ld_index <='1' when (state = s0) else '0';
ld_n <='1' when (state = s0 or state = s13) else '0';
ld_i <='1' when (state = s0 or state = s11) else '0';
ld_j <='1' when (state = s0 or state = s10) else '0';
sel<='0' when state=s12 else '1';

sel_0 <='1' when state = s12  else '0';
--sel_max<='1' when state =s7 else '0';
done <='1' when ( state = s14) else '0';

end bev;